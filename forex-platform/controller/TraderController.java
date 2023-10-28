package com.example.fdmgroup.forexplatform.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fdmgroup.forexplatform.model.Trader;
import com.example.fdmgroup.forexplatform.dto.RefundDetail;
import com.example.fdmgroup.forexplatform.model.Balance;
import com.example.fdmgroup.forexplatform.model.Order;
import com.example.fdmgroup.forexplatform.model.Portfolio;
import com.example.fdmgroup.forexplatform.model.Wallet;
import com.example.fdmgroup.forexplatform.service.TraderService;
import com.example.fdmgroup.forexplatform.service.BalanceService;
import com.example.fdmgroup.forexplatform.service.GeneralService;
import com.example.fdmgroup.forexplatform.service.OrderService;
import com.example.fdmgroup.forexplatform.service.PortfolioService;
import com.example.fdmgroup.forexplatform.service.WalletService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/broker")
public class TraderController {
	
	private final TraderService traderService;
	private final PortfolioService portfolioService;
	private final WalletService walletService;
	private final OrderService orderService;
	private final BalanceService balanceService;
	private final GeneralService generalService;
	
	public TraderController(TraderService traderService, 
			PortfolioService portfolioService,
			WalletService walletService,
			OrderService orderService,
			BalanceService balanceService,
			GeneralService generalService) {
		super();
		this.traderService = traderService;
		this.portfolioService = portfolioService;
		this.walletService = walletService;
		this.orderService = orderService;
		this.balanceService = balanceService;
		this.generalService = generalService;
	}

	@GetMapping("/")
	public String goToWebsite() {
		return "website";
	}
	
	@GetMapping("/register")
	public String goToRegistrationPage(Model model) {
		model.addAttribute("newTrader", new Trader());
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegistration(@RequestParam("confirm-password") String confirmPassword,
			HttpSession registerSession,
			Trader trader,
			Portfolio portfolio,
			Wallet wallet) {
		String email = trader.getEmail();
		String password = trader.getPassword();
		if (traderService.registerSuccessfully(trader, email, password, confirmPassword)) {
			registerSession.setAttribute("email", email);
			registerSession.setAttribute("password", password);
			portfolioService.createNewPortfolio(portfolio);
			walletService.createNewWallet(wallet);
			traderService.updatePortfolioAndWalletId(trader, portfolio, wallet);
			return "redirect:login";
		}
		if (traderService.emailExists(email)) {
			registerSession.setAttribute("email", email);
			registerSession.setAttribute("password", password);
			return "redirect:login";
		}
		return "redirect:register";
	}
	
	@GetMapping("/login")
	public String goToLoginPage(HttpSession registerSession,
			Model model) {
		model.addAttribute("email", (String) registerSession.getAttribute("email"));
		model.addAttribute("password", (String) registerSession.getAttribute("password"));
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(@RequestParam("e-mail") String email,
			@RequestParam("password") String password,
			HttpSession loginSession) {
		if (traderService.loginSuccessfully(email, password)) {
			loginSession.setAttribute("traderID", traderService.retrieveIdByEmail(email));
			return "redirect:home";
		}
		return "redirect:login";
	}
	
	@GetMapping("/home")
	public String goToHomePage(HttpSession loginSession,
			Model model) {
		long traderID = (Long) loginSession.getAttribute("traderID");
		model.addAttribute("trader", traderService.retrieveTraderById(traderID));
		return "home";
	}
	
	@GetMapping("/topup")
	public String topUpWallet(Model model) {		
		model.addAttribute("newBalance", new Balance());
	    model.addAttribute("codes", generalService.retrieveSymbols());
		return "topup";
	}
	
	@PostMapping("/topup")
	public String handleTopUp(HttpSession loginSession,
			Balance newBalance) {
		// in reality, send request to trader's bank account to check sufficient balance
		long traderID = (Long) loginSession.getAttribute("traderID");
		Wallet traderWallet = walletService.retrieveWalletByTraderId(traderID);
		boolean containsCurrencyCode = traderWallet.getBalancies()
					.stream()
					.map(balance -> balance.getCurrencyCode())
					.anyMatch(code -> code.equals(newBalance.getCurrencyCode()));
		List<Balance> updatedBalances = traderWallet.getBalancies()
					.stream()
					.filter(balance -> ! balance.getCurrencyCode().equals(newBalance.getCurrencyCode()))
					.collect(Collectors.toList());
		Balance savedBalance;
		if (containsCurrencyCode) {
			Balance retrievedBalance = traderWallet.getBalancies()
					.stream()
					.filter(balance -> balance.getCurrencyCode().equals(newBalance.getCurrencyCode()))
					.collect(Collectors.toList())
					.get(0);
			retrievedBalance.increase(newBalance.getAmount());
			savedBalance = balanceService.createNewBalance(retrievedBalance);
			updatedBalances.add(savedBalance);
			traderWallet.setBalancies(updatedBalances);
		} else {
			newBalance.setWallet(traderWallet);
			savedBalance = balanceService.createNewBalance(newBalance);
			traderWallet.addBalance(savedBalance);
		}
		if (newBalance.getCurrencyCode().toUpperCase().equals("USD")) {
			traderWallet.deposit(newBalance.getAmount());
		}
		if (! newBalance.getCurrencyCode().toUpperCase().equals("USD")) {
			float amount = generalService.convert(newBalance.getCurrencyCode(), "USD", newBalance.getAmount());
			traderWallet.deposit(amount);
		}
		walletService.updateWallet(traderWallet);
		return "redirect:wallet";
	}
	
	@GetMapping("/refund")
	public String refundWalletBalance(HttpSession loginSession,
			Model model) {
		long traderID = (Long) loginSession.getAttribute("traderID");
		List<String> refundCurrencies = walletService.retrieveWalletByTraderId(traderID).getBalancies()
				.stream()
				.map(balance -> balance.getCurrencyCode())
				.collect(Collectors.toList());
		Set<Float> maximumRefundAmounts = walletService.retrieveWalletByTraderId(traderID).getBalancies()
				.stream()
				.map(balance -> balance.getAmount())
				.collect(Collectors.toSet());
		model.addAttribute("refundCurrencies", refundCurrencies);
		model.addAttribute("maximumRefundAmounts", maximumRefundAmounts);
		model.addAttribute("refundDetail", new RefundDetail());
		return "refund";
	}
	
	@PostMapping("/refund")
	public String handleRefund(HttpSession loginSession,
			RefundDetail refundDetail) {
		long traderID = (Long) loginSession.getAttribute("traderID");
		Wallet traderWallet = walletService.retrieveWalletByTraderId(traderID);
		float amount = refundDetail.getAmount();
		if (! refundDetail.getCurrencyCode().equals("USD")) {
			amount = generalService.convert(refundDetail.getCurrencyCode(), "USD", refundDetail.getAmount());
		}
		traderWallet.withdraw(amount);
		Balance retrievedBalance = traderWallet.getBalancies()
				.stream()
				.filter(balance -> balance.getCurrencyCode().equals(refundDetail.getCurrencyCode()))
				.collect(Collectors.toList())
				.get(0);
		List<Balance> updatedBalances = traderWallet.getBalancies()
				.stream()
				.filter(balance -> ! balance.getCurrencyCode().equals(refundDetail.getCurrencyCode()))
				.collect(Collectors.toList());
		retrievedBalance.decrease(refundDetail.getAmount());
		Balance savedBalance = balanceService.createNewBalance(retrievedBalance);
		updatedBalances.add(savedBalance);
		traderWallet.setBalancies(updatedBalances);
		walletService.updateWallet(traderWallet);
		return "redirect:wallet";
	}
	
	@GetMapping("/wallet")
	public String viewBalances(HttpSession loginSession,
			Model model) {
		long traderID = (Long) loginSession.getAttribute("traderID");
		List<Balance> balances = walletService.retrieveWalletByTraderId(traderID).getBalancies()
				.stream()
				.filter(balance -> balance.getAmount() != 0)
				.collect(Collectors.toList());
		float totalValue = walletService.retrieveWalletByTraderId(traderID).getTotalValue();
		model.addAttribute("balances", balances);
		model.addAttribute("totalValue", totalValue);
		return "wallet";
	}
	
	@GetMapping("/order")
	public String goToOrderPage(HttpSession loginSession,
			Model model) {
		model.addAttribute("traderID", (Long) loginSession.getAttribute("traderID"));
		model.addAttribute("newOrder", new Order());
		model.addAttribute("buySellCurrencies", generalService.retrieveSymbols());
//		model.addAttribute("price", generalService.retrieveCurrencyRate("USD", null));
		return "order";
	}

	@PostMapping("/order")
	public String handleOrder(HttpSession loginSession,
			Order order) {
		long traderID = (Long) loginSession.getAttribute("traderID");
		Trader trader = traderService.retrieveTraderById(traderID);
		Wallet traderWallet = walletService.retrieveWalletByTraderId(traderID);
		String buySellCurrency = order.getCurrencyCode();
		float unitPrice = generalService.retrieveCurrencyRate("USD", buySellCurrency);
		float marketPrice = unitPrice * order.getQuantity();
		if (order.getSideType().equalsIgnoreCase("buy") && traderWallet.getTotalValue() >= marketPrice) {
			System.out.println(unitPrice);
			order.setUnitPrice(unitPrice);
			order.setTotalPrice(marketPrice);
			order.setTrader(trader);
			orderService.createNewOrder(order);
			trader.addOrder(order);
			traderService.updateTrader(trader);
			traderWallet.withdraw(marketPrice);
			traderWallet.getBalancies().stream().filter(balance -> balance.getCurrencyCode().equals("USD")).collect(Collectors.toList()).get(0).decrease(marketPrice);
			walletService.updateWallet(traderWallet);
			return "redirect:home";
		}
		if (order.getSideType().equalsIgnoreCase("sell")) {
			
		}
		return "redirect:order";
	}
		
//	@PostMapping("/order")
//	public String handleOrder(HttpSession loginSession,
//			Order order) {
//		long traderID = (Long) loginSession.getAttribute("traderID");
//		List<String> withGetCurrencies = walletService.retrieveWalletByTraderId(traderID).getBalancies()
//				.stream()
//				.filter(balance -> balance.getAmount() != 0)
//				.map(balance -> balance.getCurrencyCode())
//				.collect(Collectors.toList());
//		order.setTrader(traderService.retrieveTraderById(traderID));
//		if (orderService.orderSuccessfully(order)) {
//			return "redirect:home";
//		}
//		return "redirect:order";
//	}
	
	@GetMapping("/board")
	public String goToOrderBoardPage(Model model) {
		model.addAttribute("pendingOrders", orderService.retrieveAllOrdersByStatus("new"));
		return "board";
	}
	
	@GetMapping("/portfolio")
	public String viewPortfolio(HttpSession loginSession) {
		long traderID = (Long) loginSession.getAttribute("traderID");		
		return "portfolio";
	}

	@GetMapping("/logout")
	public String handleLogout(HttpSession loginSession) {
		loginSession.invalidate();
		return "website";
	}
}

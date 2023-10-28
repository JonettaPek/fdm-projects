#!/usr/bin/env python3
"""
    Author: Pek, Jonetta
    Date: 6 October 2023
    Purpose: Single-player word guessing game with limited number of tries
"""


import random


class Checker():

    def __init__(self):
        pass

    
    def check_guess(self, guess: str, word: str) -> [list | bool]:
        if guess in word:
            return [index for index in range(len(word)) if word[index] == guess]
        return False


    def check_word(self, guess: str, word: str) -> bool:
        return True if guess == word else False
    

class Game():
    __WORDS = ['Harry', 'Hermione', 'Ron', 'Dobby', 'Hagrid', 'Dumbledore', 'Minerva', 'Sirius']

    def __init__(self):
        self.__num_guess: int = 0
        self.__solution: str = self.__WORDS[random.randint(0,len(self.__WORDS)-1)].lower()
        self.__word: list = ['_'for _ in self.__solution]


    def display_word(self) -> None:
        print(f'The word is {" ".join(self.__word)}.')


    def replace_word(self, guess: str, indices: [list | bool]) -> None:
        if indices:
            for index in indices:
                self.__word[index] = guess


    def increase_count(self):
        if self.__num_guess < Player.MAX_GUESS():
            self.__num_guess += 1


    def is_solved(self) -> bool:
        return ''.join(self.__word) == self.__solution


    def instant_solve(self) -> None:
        self.__word = self.__solution.split()
        

    @property
    def num_guess(self):
        return self.__num_guess
    

    @property
    def solution(self):
        return self.__solution


class Player():
    __MAX_GUESS = 8

    def __init__(self):
        self.__game = Game()
        self.__checker = Checker()
    

    def guess(self) -> str:
        return input('Enter a letter:\n>>>').lower()


    def is_alive(self) -> bool:
        return True if self.__game.num_guess < Player.MAX_GUESS() else False
    

    def win(self) -> bool:
        print('You win!')
        return True


    def lose(self) -> bool:
        print('You lose!')
        return False


    def play(self):
        while self.is_alive():
            self.__game.display_word()
            guess: str = self.guess()
            # Instant win
            if len(guess) == len(self.__game.solution) and self.__checker.check_word(guess, self.__game.solution):
                self.__game.instant_solve()
                self.win()
                break
            is_present: [list | bool] = self.__checker.check_guess(guess, self.__game.solution)
            if is_present:
                self.__game.replace_word(guess, is_present)
            if not is_present:
                self.__game.increase_count()
            if self.__game.is_solved():
                self.win()
                break
        if not self.__game.is_solved():
            self.lose()

    @classmethod
    def MAX_GUESS(cls):
        return cls.__MAX_GUESS
        
def loop():
    ans = input('Would you like to play a game?(y/n):\n>>>')
    if ans[0].lower() == 'y':
        player = Player()
        player.play()
    else:
        return

if __name__ == '__main__':
    while True:
        loop()

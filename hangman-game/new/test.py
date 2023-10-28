#!/usr/bin/env python3
"""
    Author: Pek, Jonetta
    Date: 6 October 2023
    Purpose: Unit tests for Hangman.py
"""


import os
from hangman import Checker

__PROGRAM = './hangman.py'


def test_exists():
    """exists"""
    assert os.path.isfile(__PROGRAM)


def test_checker_check_guess_returns_list_when_present():
    """check_guess() returns a list of indices where there are matches"""
    checker = Checker()
    assert checker.check_guess('a', 'minerva') == [6]


def test_checker_check_guess_returns_false_when_absent():
    """check_guess() returns False when there is no match at all"""
    checker = Checker()
    assert checker.check_guess('u', 'minerva') == False


def test_checker_check_word_returns_true_when_match():
    """check_word() returns True where there is an exact match"""
    checker = Checker()
    assert checker.check_word('minerva', 'minerva') == True

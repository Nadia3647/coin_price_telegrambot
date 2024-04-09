# Coin Price Telegram Bot

This is a Telegram bot that provides information about the prices of coins.

## Features

- Get the current price of a coin
- Add a coin to favorites for receiving notifications if its price changes
- Command support

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.


## Usage

1. Find the bot on Telegram by searching for its username.
2. Use the `/start` command to start the bot.
3. Use the `/price` command followed by the coin's nominal, year, and mint to get the current price of a coin. For example: `/price 10 2001 ММД`
4. Use the `/track` command followed by the coin's nominal, year, and mint to add the coin to your favorites. For example: `/track 2 2000 СПМД`

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
- [Telegram Bot API](https://core.telegram.org/bots/api) - Used to interact with the Telegram Bot
- [Jsoup](https://jsoup.org/) - Used to fetch coin prices
- PostgreSQL - Used for data storage


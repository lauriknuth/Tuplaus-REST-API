# Game Engine for "Tuplaus" in Video Poker

## Background

Video poker is a casino game based on five-card draw poker which is played on a computerised console.

If the player wins a hand in the Finnish (Ray slot machine) version of the game, then they get to decide if they wish to try "Tuplaus" (double down) on their winnings or if they prefer to just cash out their winnings.

For "Tuplaus" the player needs to guess whether the topmost card of the deck is low (pieni) (ace to six) or high (suuri) (eight to king).

If the player's guess is wrong or the card is a seven, they lose their bet. If their guess is right, they double their bet.

## API

This is a game engine for the "Tuplaus" in the Video Poker game.

It has the following endpoints which have not been secured however.

### Endpoints

Create player: 

`POST /api/players`

Get all players: 

`GET /api/players`

Get a player by id: 

`GET /api/players/{id}`

Cash out - move player winnings to player balance: 

`PUT /api/players/cashout/{id}`

Get all game data records in the database:

`GET /api/games`

Play one round of "Tuplaus":

`PUT /api/games/{player_id}/{bet_amount}/{player_chooses_high}`

The above endpoint first deducts the bet amount from either player winnings or player balance depending on whether the player has previous winnings to use.

Then the endpoint pops the top card in a virtual deck and compares its value to the player's guess. If the player's guess is right, then they win double their bet. Otherwise they lose their bet.

Finally the endpoint updates the player's data and saves the game data in the database including the player's bet, the player's choice (high or low), dealt card, whether the player won, and the player's winnings and balance. A summary of the game data is also returned by the endpoint.

## Limitations

The application contains only required API endpoints and not full CRUD for all of the tables. For example it is not possible to delete any player or game records.

Also, the application creates a single instance of card deck (accessed as singleton) and since the application keeps on popping cards from the same deck, it could eventually run out of cards causing errors.

## Installation and testing

Clone the source code: https://github.com/LauriKnuth/Tuplaus-REST-API.git

You need to update the "spring.datasource.url" in the "application.properties" file or create a H2 database with the URL "jdbc:h2:file:./data/TuplausDB"

The endpoints have been tested using the HTTP queries saved in "Testing.http" in the test folder. The tests were done using the HTTP client plugin of IntelliJ but they work equally well on any browser.

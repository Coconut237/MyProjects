# Manual UI-Testing of User Cases

### Testing First View
- Expected Outcome: Display of Home Page
- Actual Outcome: Expected Output

### Testing of BottomNavigation
- Expected Outcome:
  By clicking on the Home Icon, the user is directed to the Home Page.
  By clicking on the Watchlist Icon, the user is directed to the Watchlist Page.
  By clicking on the Search Icon, the user is directed to the Search Page.
- Actual Outcome: Expected Output

### Testing Home Page
- Expected Outcome:
  The Home Page displays the homeFragment with title, welcoming text and brief description of app use.
- Actual Outcome: Expected Output

### Testing Search Page
- Expected Outcome:
  The Search Page displays the searchFragment with title, input possibility and searchButton.
  By typing in a movie title and clicking on the button, the user is directed on the Landing Page of the searched movie.
- Actual Outcome: Expected Output (API only works with internet connection and sometimes needs time to get started)

- Expected Outcome by searching a movie that doesn't exist:
  Redirecting to Search Page.
- Actual Outcome: Expected Output

- Expected Outcome by searching a movie which already exists on the watchlist:
  Landing Page of the searched movie without the AddButton.
- Actual Outcome: Expected Output

- Expected Outcome by clicking on the SearchButton without input:
  Staying on SearchPage & Popup "Input movie to search"
- Outcome: Landing Page with movie containing "null" in it's title
- Corrected actual Outcome: Expected Outcome

### Testing Landing Page
- Expected Outcome:
  The Landing Page displays information related to the movie searched and an addButton to add the movie to the watchlist.
  It shows the title of the movie and gives a rating and overview.
  By clicking the addButton, the movie is added to the watchlist & local database. The addButton disappears shortly.
- Actual Outcome: Expected Output

### Testing Watchlist Page
- Expected Outcome:
  The Watchlist Page display a title "Watchlist" and the added movies by the user.
  By clicking on the deleteButton the movie is removed of the watchlist and local database.
  By clicking on the movie item (not deleteButton), the user is directed to the LandingPage of the specific movie.
  By clicking on the shareButton the user is prompted to chose a social network to share his watchlist.
- Actual Outcome: Expected Output
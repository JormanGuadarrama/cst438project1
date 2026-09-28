# Guadarrama, Jorman - Project 01 Retrospective

## My work
- Merged PRs: https://github.com/JormanGuadarrama/cst438project1/pulls?q=is%3Apr+state%3Aclosed+assignee%3AJormanGuadarrama
- My issues: https://github.com/JormanGuadarrama/cst438project1/issues?q=is%3Aissue+state%3Aclosed+assignee%3AJormanGuadarrama
- What I built: Profile Screen, Login Screen, Database, Profile Pic Screen, Profile Icons

## Biggest challenge
My biggest challenge was getting the API connection working and then displaying the information from the API.
I had to set up Retrofit with OkHttp and Gson, and it took some trial and error to get the calls and the data
parsing right. Once the connection worked, I still had to figure out how to take the data coming back from the
API and actually display it, which meant slowing down and understanding how the response was structured before
I could use it.

## Most valuable thing I learned
The most valuable thing I learned was how an API connection works from end to end. Before this project
I had never set up Retrofit, OkHttp, and Gson together, so I didn't really understand what happens between
sending a request and getting usable data back. Building that connection taught me how the pieces fit together:
how the client sends the call, how the response comes back, and how Gson turns that response into objects I can
actually use in the app. Now I feel much more comfortable working with APIs and know what to check when
something isn't coming through the way I expect.

## What I carry into Project 02
1. I want my issues to be clearer and more specific, so anyone reading them knows exactly what needs to be done without asking me.
2. I want my PR review notes to be more useful, so instead of a quick approval I leave feedback that actually helps the other person improve their code.
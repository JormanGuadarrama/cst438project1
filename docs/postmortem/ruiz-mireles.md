# Hugo Ruiz-Mireles - Project 01 Retrospective

## My work
- Merged PRs: [Link to list of PR's assigned to me](https://github.com/JormanGuadarrama/cst438project1/pulls?q=is%3Apr+state%3Aclosed+author%3AHugo-RM)
- My issues: [Link to list of Issues assigned to me](https://github.com/JormanGuadarrama/cst438project1/issues?q=is%3Aissue+state%3Aclosed+assignee%3AHugo-RM)
- What I built: Database Scaffolding, Sign Up Page, README, Merge Conflict Resolutions
- Reviews: I reviewed and merged teammates' PRs #15, #25, #46, #50, #59, #63, #65, #66.

## Biggest challenge
The biggest challenge I faced was fixing a merge conflict. Both branches changed `HomeScreen.kt`. One side added the selected artist's album art, bio and tags. Another side made the profile picture and color load from the logged-in user through the new `AuthViewModel`. That made it hard because I didn't write either side, and taking just one version would have broken either the artist details or the user's profile on the home screen. I was able to make it so both worked together

## Most valuable thing I learned
Setting up shared infrastructure early matters a lot more than I thought it would've. The Room scaffolding had only a placeholder entity, but once it was merged, teammates could add the `UserEntity`, DAO and repository without waiting on me or setting up Room again.

## What I carry into Project 02
1. **I will set up infrastructure on GitHub Actions to make it easier and quicker to review PR's** I realized I was spending a lot of time setting up my local environment, running the emulator, and going around the app to ensure it was still working. I feel it'd save me a lot of time to simply create as many automated tests upon creating a PR so I can focus on simply checking the code itself. I will know it worked if the P2 repo has a GitHub Actions workflow I wrote that runs the unit tests on every PR, and every P2 PR I approve shows that check passing before I approve it. (Our P1 repo never had a workflow in `.github/workflows`.)
2. **I will chart out the planned approach to the overall project more thoroughly than simply going with the flow** The biggest overall challenge for everyone was dealing with differing gradles and stuff. I feel like if I spent more time looking into the criteria of the project and what we wanted to do, I could've simply made a PR with all the additional dependencies we'd need right away. I feel this would save us the headache later down the line.
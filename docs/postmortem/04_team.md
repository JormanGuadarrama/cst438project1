# Project 01 Post Mortem - team 04 / cst438project1

## Context
We set out to build an artist lookup app that lets users search for artists and view details about them. 
The final app stayed very close to our original design because we had a clear idea from the beginning. 
This made it easier to choose features and implement them without major changes.

## By the numbers
- Issues opened: 0 (https://github.com/JormanGuadarrama/cst438project1/issues?q=is%3Aissue+state%3Aclosed) | closed: 38
- Pull requests opened: 0 (https://github.com/JormanGuadarrama/cst438project1/pulls) | merged: 34
- Planned at kickoff: 1 stories | done: 12

## What went well
1. Clear initial design – Having a solid plan from the beginning kept the project focused and prevented scope creep.
2. API integration success – Getting the API connection working across all teammates’ devices was a major win and made the app feel complete.

## What went wrong
1. Gradle setup issues – Cause: Different operating systems caused inconsistent Gradle behavior. It took troubleshooting to get everyone’s environment aligned.
2. Cross‑device configuration differences – Cause: SDK versions and emulator setups weren’t identical at first, which slowed down early development until everything was standardized.

## Advice to our next teams
1. Make sure everyone fully understands the app being built. Clear shared understanding prevents confusion and keeps the project aligned.
2. Test everything. Each pull request should include tests so issues are caught early instead of after merging.
3. Use a proper .gitignore early. This prevents unnecessary files from causing conflicts and keeps everyone’s Gradle setup consistent.
4. Update main before writing new code. Pulling the latest changes first avoids merge conflicts and keeps everyone working on the same version of the project.

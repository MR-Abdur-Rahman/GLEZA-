# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A coding-challenge repo ("Cargo Bridges"). The whole task is to make `cjp.TetherPlan.minCost(int n, long[][] links)` return the cost of a minimum spanning tree over stations `1..n`, or `-1` if they can't all be connected. Each link is `{a, b, w}`, with 1-indexed station ids. See `README.md` for examples and constraints (`n ≤ 100k`, `m ≤ 300k`, `w ≤ 1e9`, so sums need `long`).

## Rules that affect how you work here

- **Only `src/main/java/cjp/` may be changed.** Do not edit `pom.xml` or `.github/workflows/validate.yml`, and do not commit test files. A hidden JUnit 5 suite is overlaid at validation time.
- **Keep the `minCost` signature exactly as it is.** The hidden tests call it directly.
- **Every push to the question branch is scored.** A full pass locks the repo read-only, so only push when the user explicitly asks.
- Edge cases the README calls out: cycle edges in equal-weight graphs must be skipped, disconnected inputs return `-1`, and `n <= 1` returns `0`.

## Commands

Java 21 and Maven (JUnit Jupiter 5.10.2, Surefire 3.2.5):

- Build: `mvn -q compile`
- Run tests: `mvn test` (the repo ships with no tests, so only local scratch tests will run)
- Single test: `mvn test -Dtest=ClassName#methodName`

To check correctness locally, put throwaway tests under `src/test/java/cjp/` and don't commit them, or run a scratch `main` outside the repo.

## Current state of the code

The header comment in `TetherPlan.java` still says "BROKEN on purpose" and lists two bugs: comparing immediate parents instead of roots, and a missing connectivity check. The implementation already fixes both. It runs Kruskal with union-by-rank, path-halving `find`, and a final `used == n - 1` check. That comment is stale, so read the code itself before assuming anything is broken.

# Cargo Bridges  `MEDIUM`

The fleet wants to link all `N` orbital stations with cargo tethers so any
station can reach any other (directly or through intermediates). Each
candidate tether `{a, b, w}` costs `w` to fabricate.

## Your task

Fix `cjp.TetherPlan` so that:

```java
public static long minCost(int n, long[][] links)
```

returns the minimum total cost to connect ALL stations (a minimum spanning
tree), or `-1` if the stations cannot all be connected.

## Examples

- Triangle `1-2 (1)`, `2-3 (2)`, `1-3 (3)` → `3`
- Equal-cost triangle (all `1`) → `2` — a cycle edge must never be counted!
- `n=4` with only `1-2` available → `-1`

## Constraints

- `1 <= n <= 100_000`, `0 <= m <= 300_000`, `1 <= w <= 10^9`.
- Kruskal with a proper disjoint-set union is the intended fix.

## How you are scored

- Hidden tests run automatically on **every push to this branch** (the question
  branch — pushes to other branches are ignored). Check the Actions tab for logs.
- A **full pass scores the challenge** and locks this repository read-only.
  Partial credit may apply if the organizers configured scoring tiers.
- Only `src/main/java/cjp/` is yours to change. Do **not** modify `pom.xml`,
  `.github/workflows/validate.yml`, or add test files — the hidden suite is
  overlaid at validation time.
- Keep the method signature exactly as given; the tests call it directly.

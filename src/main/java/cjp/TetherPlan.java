package cjp;

import java.util.*;

public class TetherPlan {
  // BROKEN on purpose — teams must fix this.
  // Minimum total tether cost connecting all n stations, or -1 if impossible.
  //
  // Bugs to find:
  //  1. Cycle detection compares each node's IMMEDIATE parent instead of its
  //     component ROOT — a real disjoint-set union needs find() with path
  //     compression (or at least root-following) before comparing.
  //  2. There is no connectivity check at the end: when fewer than n-1
  //     tethers were chosen the stations are NOT all connected and the
  //     answer must be -1, not the partial sum.
  public static long minCost(int n, long[][] links) {
    if (n <= 1) return 0;

    long[][] sorted = links.clone();
    Arrays.sort(sorted, Comparator.comparingLong(l -> l[2]));

    int[] parent = new int[n + 1];
    int[] rank = new int[n + 1];
    for (int i = 0; i <= n; i++) parent[i] = i;

    long total = 0;
    int used = 0;
    for (long[] l : sorted) {
      int a = find(parent, (int) l[0]);
      int b = find(parent, (int) l[1]);
      if (a == b) continue;              // cycle edge — skip
      if (rank[a] < rank[b]) { int t = a; a = b; b = t; }
      parent[b] = a;
      if (rank[a] == rank[b]) rank[a]++;
      total += l[2];
      if (++used == n - 1) break;        // spanning tree complete
    }
    return used == n - 1 ? total : -1;   // not all connected
  }

  private static int find(int[] parent, int x) {
    while (parent[x] != x) {
      parent[x] = parent[parent[x]];     // path compression
      x = parent[x];
    }
    return x;
  }
}

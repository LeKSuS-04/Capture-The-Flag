def search_grid(grid: list[list[str]], word: str) -> list[tuple[int, int]]:
    dirs = [(0, 1), (1, 0), (0, -1), (-1, 0), (1, 1), (-1, -1), (1, -1), (-1, 1)]
    def check(i, j, idx, d):
        if idx == len(word):
            return True
        else:
            di = (i + d[0] * idx) % len(grid)
            dj = (j + d[1] * idx) % len(grid[0])

            if grid[di][dj] != word[idx]:
                return False
            else:
                return check(i, j, idx + 1, d)

    ans = []
    for i, line in enumerate(grid):
        for j, c in enumerate(line):
            for d in dirs:
                if check(i, j, 0, d):
                    idx = 0
                    while idx < len(word):
                        ans += [(j + d[1] * idx, i + d[0] * idx)]
                        idx += 1
                    return ans
#[cfg(test)]
mod day2 {
    use std::fs;
    use aoc_2023::day2;

    #[test]
    fn test_part1() {
        let input = fs::read_to_string("input/2023/day2.txt");
        let parsed = day2::parse_games(input.unwrap().as_str());
        assert_eq!(2505, day2::part1(&parsed))
    }

    #[test]
    fn test_part2() {
        let input = fs::read_to_string("input/2023/day2.txt");
        let parsed = day2::parse_games(input.unwrap().as_str());
        assert_eq!(70265, day2::part2(&parsed))
    }
}
#[cfg(test)]
mod day1 {
    use std::fs;
    use aoc_2023::day4;

    #[test]
    fn test_part1() {
        let input = fs::read_to_string("input/2023/day4.txt");
        let parsed = day4::parse_cards(input.unwrap().as_str());
        assert_eq!(21105, day4::part1(&parsed))
    }

    #[test]
    fn test_part2() {
        let input = fs::read_to_string("input/2023/day4.txt");
        let parsed = day4::parse_cards(input.unwrap().as_str());
        assert_eq!(5329815, day4::part2(&parsed))
    }
}
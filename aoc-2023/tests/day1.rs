#[cfg(test)]
mod day1 {
    use std::fs;
    use aoc_2023::day1;

    #[test]
    fn test_part1() {
        let input = fs::read_to_string("input/2023/day1.txt");
        assert_eq!(57346, day1::part1(input.unwrap().as_str()))
    }

    #[test]
    fn test_part2() {
        let input = fs::read_to_string("input/2023/day1.txt");
        assert_eq!(57345, day1::part2(input.unwrap().as_str()))
    }
}
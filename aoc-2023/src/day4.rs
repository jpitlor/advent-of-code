use regex::Regex;

#[aoc_generator(day4, part1)]
#[aoc_generator(day4, part2)]
pub fn parse_cards(input: &str) -> Vec<i32> {
    let regex = Regex::new(r": (.*) \| (.*)").unwrap();
    input
        .lines()
        .map(|line| {
            let (_, [winners, my_numbers]) = regex.captures(line).unwrap().extract();
            let winning_numbers = winners
                .split(" ")
                .filter_map(|n| n.parse::<i32>().ok())
                .collect::<Vec<i32>>();
            my_numbers
                .split(" ")
                .filter_map(|n| n.parse::<i32>().ok())
                .filter(|n| winning_numbers.contains(n))
                .count() as i32
        })
        .collect()
}

#[aoc(day4, part1)]
pub fn part1(input: &[i32]) -> i32 {
    input
        .iter()
        .map(|&winners| if winners == 0 { 0 } else { 2i32.pow(winners as u32 - 1) })
        .sum()
}

#[aoc(day4, part2)]
pub fn part2(input: &[i32]) -> i32 {
    let mut cards = input.iter().map(|_| 1).collect::<Vec<i32>>();
    for i in 0..input.len() {
        if input[i] == 0 {
            continue
        }
        
        for j in (i + 1)..(i + 1 + input[i] as usize) {
            cards[j] += cards[i];
        }
    }
    cards.iter().sum()
}
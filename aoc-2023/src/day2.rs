use regex::Regex;

struct BagPull {
    red: i32,
    green: i32,
    blue: i32
}

pub struct Game {
    id: i32,
    pulls: Vec<BagPull>
}

fn is_game_possible(game: &Game, red: i32, green: i32, blue: i32) -> bool {
    game.pulls.iter().all(|p| p.red <= red && p.blue <= blue && p.green <= green)
}

fn get_game_power(game: &Game) -> i32 {
    game.pulls.iter().map(|p| p.red).max().unwrap()
        * game.pulls.iter().map(|p| p.green).max().unwrap()
        * game.pulls.iter().map(|p| p.blue).max().unwrap()
}

#[aoc_generator(day2, part1)]
#[aoc_generator(day2, part2)]
pub fn parse_games(input: &str) -> Vec<Game> {
    let game_regex = Regex::new(r"Game (\d+): (.*)").unwrap();
    let pull_regex = Regex::new(r"(\d+) (\w+)").unwrap();
    input
        .lines()
        .map(|line| {
            let (_, [game_id, pulls]) = game_regex.captures(line).unwrap().extract();
            Game {
                id: game_id.parse::<i32>().unwrap(),
                pulls: pulls
                    .split("; ")
                    .map(|pull| {
                        let mut red = 0;
                        let mut green = 0;
                        let mut blue = 0;
                        for (_, [count, color]) in pull_regex.captures_iter(pull).map(|p| p.extract()) {
                            match color {
                                "red" => {
                                    red = count.parse::<i32>().unwrap()
                                }
                                "green" => {
                                    green = count.parse::<i32>().unwrap()
                                }
                                "blue" => {
                                    blue = count.parse::<i32>().unwrap()
                                }
                                _ => {}
                            }
                        }

                        BagPull { red, blue, green }
                    })
                    .collect()
            }
        })
        .collect()
}

#[aoc(day2, part1)]
pub fn part1(games: &[Game]) -> i32 {
    games
        .iter()
        .filter(|&g| is_game_possible(g, 12, 13, 14))
        .map(|g| g.id)
        .sum()
}

#[aoc(day2, part2)]
pub fn part2(games: &[Game]) -> i32 {
    games.iter().map(get_game_power).sum()
}

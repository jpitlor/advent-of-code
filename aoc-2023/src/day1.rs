use aoc_runner_derive::aoc;

fn get_calibration_number(line: &str, use_words: bool) -> i32 {
    let mut first_number: Option<i32> = None;
    let mut last_number: Option<i32> = None;
    let mut use_number = |n| {
        let _ = first_number.get_or_insert(n);
        let _ = last_number.insert(n);
    };

    let mut i = 0;
    while i < line.len() {
        let c = line.chars().nth(i);
        match c {
            Some('1'..='9') => {
                use_number(c.unwrap().to_string().parse::<i32>().unwrap());
                i += 1;
            }
            Some('o') if use_words => {
                if i + 3 <= line.len() && &line[i..i+3] == "one" {
                    use_number(1);
                    i += 2;
                    continue;
                }
                i += 1;
            }
            Some('t') if use_words => {
                if i + 3 <= line.len() && &line[i..i+3] == "two" {
                    use_number(2);
                    i += 2;
                    continue;
                }
                if i + 5 <= line.len() && &line[i..i+5] == "three" {
                    use_number(3);
                    i += 4;
                    continue;
                }
                i += 1;
            }
            Some('f') if use_words => {
                if i + 4 <= line.len() && &line[i..i+4] == "four" {
                    use_number(4);
                    i += 3;
                    continue;
                }
                if i + 4 <= line.len() && &line[i..i+4] == "five" {
                    use_number(5);
                    i += 3;
                    continue;
                }
                i += 1;
            }
            Some('s') if use_words => {
                if i + 3 <= line.len() && &line[i..i+3] == "six" {
                    use_number(6);
                    i += 2;
                    continue;
                }
                if i + 5 <= line.len() && &line[i..i+5] == "seven" {
                    use_number(7);
                    i += 4;
                    continue;
                }
                i += 1;
            }
            Some('e') if use_words => {
                if i + 5 <= line.len() && &line[i..i+5] == "eight" {
                    use_number(8);
                    i += 4;
                    continue;
                }
                i += 1;
            }
            Some('n') if use_words => {
                if i + 4 <= line.len() && &line[i..i+4] == "nine" {
                    use_number(9);
                    i += 3;
                    continue;
                }
                i += 1;
            }
            _ => i += 1
        }
    }
    first_number.unwrap() * 10 + last_number.unwrap_or(first_number.unwrap())
}

#[aoc(day1, part1)]
pub fn part1(input: &str) -> i32 {
    input.lines().map(|l| get_calibration_number(l, false)).sum()
}

#[aoc(day1, part2)]
pub fn part2(input: &str) -> i32 {
    // "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen"
    //     .lines()
    //     .map(|l| get_calibration_number(l, true))
    //     .sum()
    input.lines().map(|l| get_calibration_number(l, true)).sum()
}

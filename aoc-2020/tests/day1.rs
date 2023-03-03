extern crate core;

mod utils;

#[cfg(test)]
mod day1 {
    use crate::utils;

    #[test]
    fn part1() {
        let input: Vec<i32> = utils::read_input_for_day(1)
            .iter()
            .map(|i| i.parse::<i32>().unwrap())
            .collect();
        for i in 0..input.len() {
            for j in (i + 1)..input.len() {
                if input[i] + input[j] != 2020 {
                    continue;
                }

                assert_eq!(974304, input[i] * input[j]);
                return;
            }
        }
    }

    #[test]
    fn part2() {
        let input: Vec<i32> = utils::read_input_for_day(1)
            .iter()
            .map(|i| i.parse::<i32>().unwrap())
            .collect();
        for i in 0..input.len() {
            for j in (i + 1)..input.len() {
                for k in (i + 2)..input.len() {
                    if input[i] + input[j] + input[k] != 2020 {
                        continue;
                    }

                    assert_eq!(236430480, input[i] * input[j] * input[k]);
                    return;
                }
            }
        }
    }
}

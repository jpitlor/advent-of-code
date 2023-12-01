#[cfg(test)]
mod day1 {
    use aoc_2023::setup::download_input;

    #[test]
    fn test_add() {
        download_input(1)?;

        assert_eq!(add(1, 2), 3);
    }

    #[test]
    fn test_bad_add() {
        download_input(1)?;

        assert_eq!(bad_add(1, 2), 3);
    }
}
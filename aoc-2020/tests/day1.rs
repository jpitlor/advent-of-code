mod utils;

#[test]
fn day1() {
    let input = utils::read_input(1);
    for line in input.iter() {
        println!("{}", line);
    }
}

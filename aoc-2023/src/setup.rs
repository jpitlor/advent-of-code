use std::fmt::format;
use std::fs::File;
use std::io::Write;
use std::path::Path;

pub fn download_input(day: i32) -> Result<(), ()> {
    let file = format!("input/day{}.txt", day);
    if Path::new(&file).exists() {
        return Ok(());
    }

    let url = format!("https://adventofcode.com/2023/day/{}/input", day);
    let input = reqwest::blocking::get(url)?.text()?;
    File::create(&file)?.write(input.as_bytes())?;
    return Ok(());
}
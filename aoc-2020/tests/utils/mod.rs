use std::{env, fs};
use std::fs::File;
use std::io::Write;
use std::path::Path;
use std::sync::Arc;
use reqwest::blocking::{Client, ClientBuilder};
use reqwest::cookie::Jar;
use reqwest::{Url};

fn get_client() -> Client {
    let jar = Jar::default();
    let session_token = env::var("AOC_SESSION_TOKEN").expect("No AOC Session Token set");
    let cookie_string = format!("session={}", session_token);
    let url = Url::parse("https://adventofcode.com").expect("Could not parse domain to set cookie");
    jar.add_cookie_str(cookie_string.as_str(), &url);
    return ClientBuilder::new()
        .cookie_provider(Arc::new(jar))
        .build()
        .expect("Could not create client to download input");
}

pub fn read_input_for_day(day: i8) -> Vec<String> {
    let path = format!("resources/day{}.txt", day);
    if !Path::new(&path).exists() {
        let url = format!("https://adventofcode.com/2020/day/{}/input", day);
        let result = get_client()
            .get(url)
            .send()
            .expect("Could not fetch the input")
            .bytes()
            .expect("Could not transform the input to a buffer");
        File::create(&path)
            .expect("Could not create file to dump input")
            .write_all(result.as_ref())
            .expect("Could not write the input to a file");
    }

    return fs::read_to_string(path)
        .expect("Could not read input file")
        .lines()
        .map(|s| s.to_string())
        .collect();
}

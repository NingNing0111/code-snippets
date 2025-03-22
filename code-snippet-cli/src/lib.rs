use std::{
    env, fs,
    io::{self, Read},
    path::PathBuf,
};
pub mod args;
pub mod command;
pub const APP_NAME: &str = "code-snippets";
use anyhow::{Context, Result, anyhow};
use serde::{Deserialize, Serialize};

#[derive(Debug, Deserialize, Clone, Serialize)]
pub struct Config {
    pub api: Option<String>,
    pub password: Option<String>,
}

#[derive(Debug)]
pub struct CodeSnippet {
    pub title: String,
    pub description: String,
    pub language: String,
    pub category: String,
    pub password: String,
    pub api: String,
    pub content: String,
}

#[derive(Deserialize, Debug)]
struct ApiResponse {
    code: i32,
    message: String,
    data: Option<serde_json::Value>,
}

pub fn read_text_from_file(path: &str) -> String {
    let mut file = std::fs::File::open(path).unwrap();
    let mut contents = String::new();
    file.read_to_string(&mut contents).unwrap();
    contents
}

pub fn read_input_with_tip(tip_msg: &str) -> String {
    println!("{}", tip_msg);
    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("读取失败");
    return input.trim().to_string();
}

pub fn init_app_dir() -> PathBuf {
    let app_dir = get_app_dir();

    if !app_dir.exists() {
        fs::create_dir_all(&app_dir).ok(); // Create the application directory
    }
    app_dir
}

pub fn get_app_dir() -> PathBuf {
    let mut user_dir = dirs::home_dir().unwrap_or_else(|| env::temp_dir());
    user_dir.push(format!(".{}", APP_NAME.to_string()));
    user_dir
}

pub fn get_app_conf() -> Result<Config> {
    let app_dir = get_app_dir();
    let env_path = app_dir.join("app.yml");
    if !env_path.exists() {
        return Err(anyhow!(
            "Config file not found. Please use `cnpt init` to create one."
        ));
    }
    // Read the app.yml file
    let yml_str =
        fs::read_to_string(&env_path).context(format!("Failed to read {:?}", env_path))?;
    let config: Config = serde_yaml::from_str(&yml_str).context("Failed to parse config")?;
    Ok(config)
}

pub fn get_app_conf_path() -> Result<PathBuf> {
    let app_dir = get_app_dir();
    let env_path = app_dir.join("app.yml");
    Ok(env_path)
}

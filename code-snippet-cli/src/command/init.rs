use std::{fs, io::Write};

use anyhow::{Ok, Result};
use chrono::Local;
use log::info;

use crate::init_app_dir;

const INIT_TEMPLATE: &str = "api: \"\"\npassword: \"\"";

pub fn init_app() -> Result<()> {
    // Initialize the application directory. If the directory does not exist, create it; otherwise, do nothing.
    let app_dir = init_app_dir();
    // Check if there is an app.yml file under app_dir. If so, rename the app.yml file in the format: app.yml-yyyy-MM-dd
    let env_path = app_dir.join("app.yml");
    if env_path.exists() {
        // Generate a new file name: app.yml-yyyy-MM-dd
        let timestamp = Local::now().format("%Y-%m-%d-%H-%M").to_string();
        let new_env_path = app_dir.join(format!("app.yml-{}", timestamp));
        fs::rename(&env_path, &new_env_path)?;
    }
    let mut path = fs::File::create(env_path)?;
    path.write_all(INIT_TEMPLATE.as_bytes())?;
    // println!("Init successful! Config file path:{:?}", path);
    info!("Init successful! Config file path:{:?}", path);
    Ok(())
}

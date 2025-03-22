use anyhow::Result;
use log::info;

use crate::get_app_conf;

pub fn print_info() -> Result<()> {
    let config = get_app_conf()?;
    info!("api: {}", config.api.unwrap_or(String::from("")));
    info!("password: {}", config.password.unwrap_or(String::from("")));
    Ok(())
}

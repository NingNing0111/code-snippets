use anyhow::{Ok, Result};

use crate::{Config, get_app_conf, get_app_conf_path};

pub fn set(key: String, value: String) -> Result<()> {
    let mut config = get_app_conf()?;
    let mut pwd = None;
    let mut api = None;
    if key == String::from("password") {
        pwd = Some(value);
    } else if key == String::from("api") {
        api = Some(value);
    } else {
        log::error!("key only be `password` or `api`");
        return Ok(());
    }

    if pwd.is_some() {
        config.password = pwd;
    }
    if api.is_some() {
        config.api = api;
    }
    write_config(config)?;
    Ok(())
}

pub fn write_config(config: Config) -> Result<()> {
    let str = serde_yaml::to_string(&config).unwrap();
    let conf_path = get_app_conf_path()?;
    std::fs::write(conf_path, str)?;
    Ok(())
}

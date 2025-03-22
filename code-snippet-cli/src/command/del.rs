use std::collections::HashMap;

use anyhow::{Context, Ok, Result, anyhow};
use log::{error, info};
use reqwest::header::HeaderMap;

use crate::{ApiResponse, get_app_conf};

pub async fn delete(id: u64) -> Result<()> {
    let client = reqwest::ClientBuilder::new().no_proxy().build()?;
    // let client = reqwest::Client::new();
    let config = get_app_conf()?;
    let mut headers = HeaderMap::new();
    headers.insert("Content-Type", "application/json".parse().unwrap());
    headers.insert(
        "Authorization",
        format!("{}", config.password.unwrap()).parse().unwrap(),
    );
    let mut data = HashMap::new();
    data.insert("id", id);
    let response = client
        .post(&format!(
            "{}/codeSnippet/deleteCodeSnippet",
            config.api.unwrap()
        ))
        .headers(headers)
        .json(&data)
        .send()
        .await?;
    log::info!("{:?}", response);

    let json_str = response.text().await?;
    let res: ApiResponse = serde_json::from_str(&json_str)
        .context(anyhow!("HttpResponse parse fail! {}", json_str))?;
    if res.code == 0 && res.data.is_some() {
        info!("Delete successful!");
    } else {
        error!("Delete fail! msg:{}", res.message);
    }
    Ok(())
}

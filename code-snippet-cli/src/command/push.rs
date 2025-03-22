use std::collections::HashMap;

use anyhow::{Context, Ok, Result, anyhow};
use log::{error, info};
use reqwest::header::HeaderMap;

use crate::{ApiResponse, CodeSnippet};

pub async fn push(code_snippet: CodeSnippet) -> Result<()> {
    let mut headers = HeaderMap::new();
    headers.insert("Content-Type", "application/json".parse().unwrap());
    headers.insert(
        "Authorization",
        format!("{}", code_snippet.password).parse().unwrap(),
    );
    let mut data = HashMap::new();
    data.insert("title", code_snippet.title);
    data.insert("description", code_snippet.description);
    data.insert("language", code_snippet.language);
    data.insert("category", code_snippet.category);
    data.insert("content", code_snippet.content);
    let url = format!("{}/codeSnippet/addCodeSnippet", code_snippet.api);
    // 暂时不考虑代理
    let client = reqwest::ClientBuilder::new().no_proxy().build()?;
    // let client = reqwest::Client::new();
    let response = client
        .post(&url)
        .headers(headers)
        .json(&data)
        .send()
        .await?;
    // Check the status code before trying to parse the response body
    if response.status().is_success() {
        let json_str = response.text().await?;
        let res: ApiResponse = serde_json::from_str(&json_str)
            .context(anyhow!("HttpResponse parse fail! {}", json_str))?;
        if res.code == 0 && res.data.is_some() {
            info!("Push successful!");
        } else {
            error!("Push fail! msg:{}", res.message);
        }
    } else {
        error!("Request failed with status code: {}", response.status());
    }
    Ok(())
}

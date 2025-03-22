use cnpt::get_app_conf;
use reqwest::header::HeaderMap;
use std::collections::HashMap;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let args = get_app_conf()?;
    let client = reqwest::Client::new();
    let mut headers = HeaderMap::new();
    headers.insert("Content-Type", "application/json".parse().unwrap());
    headers.insert(
        "Authorization",
        format!("Bearer {}", args.password.unwrap())
            .parse()
            .unwrap(),
    );
    let mut data = HashMap::new();
    data.insert("title", "12");
    data.insert("description", "123");
    data.insert("language", "123123");
    data.insert("category", "123123");
    data.insert("content", "123123");
    let response = client
        .post(&format!("{}/codeSnippet/addCodeSnippet", args.api.unwrap()))
        .headers(headers)
        .json(&data)
        .send()
        .await?;

    println!("Response status: {:?}", response);

    Ok(())
}

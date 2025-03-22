use code_snippet::init_args;
use reqwest::header::HeaderMap;
use std::collections::HashMap;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let args = init_args().await;
    let client = reqwest::Client::new();
    let mut headers = HeaderMap::new();
    headers.insert("Content-Type", "application/json".parse().unwrap());
    headers.insert(
        "Authorization",
        format!("{}", args.password).parse().unwrap(),
    );
    let mut data = HashMap::new();
    data.insert("id", args.delete_id);
    let response = client
        .post(&format!("{}/codeSnippet/deleteCodeSnippet", args.api))
        .headers(headers)
        .json(&data)
        .send()
        .await?;

    println!("Response status: {:?}", response);

    Ok(())
}

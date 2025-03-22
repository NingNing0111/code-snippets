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
    data.insert("title", args.title);
    data.insert("description", args.description);
    data.insert("language", args.language);
    data.insert("category", args.category);
    data.insert("content", args.content);
    let response = client
        .post(&format!("{}/codeSnippet/addCodeSnippet", args.api))
        .headers(headers)
        .json(&data)
        .send()
        .await?;

    println!("Response status: {:?}", response);

    Ok(())
}

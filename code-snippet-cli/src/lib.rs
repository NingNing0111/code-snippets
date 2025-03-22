use std::io::Read;

#[derive(Debug)]
pub struct CodeSnippet {
    pub title: String,
    pub description: String,
    pub language: String,
    pub category: String,
    pub password: String,
    pub api: String,
    pub content: String,
    pub delete_id: String,
}

pub async fn init_args() -> CodeSnippet {
    dotenv::dotenv().ok();
    let password: String = std::env::var("PASSWORD")
        .expect("PASSWORD environment variable not set")
        .parse()
        .unwrap();
    let title: String = std::env::var("TITLE")
        .expect("TITLE environment variable not set")
        .parse()
        .unwrap();
    let description: String = std::env::var("DESCRIPTION").unwrap_or_default();
    let code_path: String = std::env::var("CODE_PATH")
        .expect("CODE_PATH environment variable not set")
        .parse()
        .unwrap();
    let content = read_text_from_file(code_path.as_str());
    let language: String = std::env::var("LANGUAGE").unwrap_or_else(|_| "md".to_string());
    let category: String = std::env::var("CATEGORY").unwrap_or_default();
    let api: String = std::env::var("API_URL")
        .expect("API_URL environment variable not set")
        .parse()
        .unwrap();
    let delete_id: String = std::env::var("DELETE_ID").unwrap_or_default();
    return CodeSnippet {
        title,
        description,
        language,
        category,
        password,
        api,
        content,
        delete_id,
    };
}

fn read_text_from_file(path: &str) -> String {
    let mut file = std::fs::File::open(path).unwrap();
    let mut contents = String::new();
    file.read_to_string(&mut contents).unwrap();
    contents
}

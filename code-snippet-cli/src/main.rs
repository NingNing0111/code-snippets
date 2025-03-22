use anyhow::{Ok, Result};
use clap::Parser;
use cnpt::{
    CodeSnippet,
    args::{Args, Command},
    command::{del::delete, info::print_info, init::init_app, push::push, set::set},
    get_app_conf, read_input_with_tip, read_text_from_file,
};
use env_logger;

fn init_log() -> Result<()> {
    env_logger::Builder::from_env(env_logger::Env::default().default_filter_or("info")).init();
    Ok(())
}
#[tokio::main]
async fn main() -> Result<()> {
    init_log()?;
    let args = Args::parse();
    match args.command {
        Command::Init => {
            init_app()?;
        }
        Command::Push {
            title,
            path,
            language,
            category,
        } => {
            let app_conf = get_app_conf()?;
            let content = read_text_from_file(&path);
            let description = read_input_with_tip("Please input the code snippet's description:");
            let code_snippet = CodeSnippet {
                api: app_conf.api.unwrap().clone(),
                password: app_conf.password.unwrap().clone(),
                title,
                language,
                category,
                content,
                description,
            };
            push(code_snippet).await?
        }
        Command::Set { key, value } => {
            set(key, value)?;
        }
        Command::Del { id } => {
            delete(id).await?;
        }
        Command::Info => print_info()?,
    }
    Ok(())
}

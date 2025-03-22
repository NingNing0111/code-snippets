use clap::{Parser, Subcommand};
#[derive(Parser, Debug)]
#[command(version, about, long_about = None)]
pub struct Args {
    #[command(subcommand)]
    pub command: Command,
}

#[derive(Subcommand, Debug)]
pub enum Command {
    /// init a config file
    Init,
    /// set config key-value. key only be `password` or `api`. example: `cnpt set pwd 123456`
    Set { key: String, value: String },
    /// push a code snippet to server. example: `cnpt push -t "hello world" -c "src/main.rs" -l "rust" -g "rust"`
    Push {
        #[arg(short = 't', long)]
        title: String,
        #[arg(short = 'c', long)]
        path: String,
        #[arg(short = 'l', long)]
        language: String,
        #[arg(short = 'g', long = "cg")]
        category: String,
    },
    /// delete a code snippet from server. example: `cnpt del -i 1`
    Del {
        #[arg(short = 'i', long)]
        id: u64,
    },
    /// print config info. example: `cnpt info`
    Info,
}

---
layout: post
date: 2020-06-10
title: Rust 라이브러리 프로젝트를 wasm으로 빌드하기
author: Jeon Yongtae
categories: [WebAssembly]
tags: [WebAssembly]
---

# 프로젝트 구성

Rust 라이브러리 프로젝트를 생성한다.

```shell
cargo new --lib wasm_lib_demo
```

Cargo.toml 에 다음 설정을 추가한다.

```toml
[lib]
crate-type = ['cdylib']
```

코드를 작성한다.

```rust
#[no_mangle]
pub extern "C" fn add_one( i : i32 )->i32{
i + 1
}
```

# 빌드

wasm32-unkown-unkown 이나 wasm32-wasi 타겟으로 빌드할 수 있다.

```shell
rustup target add wasm32-wasi
cargo build --target wasm32-wasi
```

cargo-wasi 를 이용해서 빌드할 수도 있다.

```powershell
cargo wasi build
```

[GitHub - bytecodealliance/cargo-wasi: A lightweight Cargo subcommand to build Rust code for the `wasm32-wasi` target](https://github.com/bytecodealliance/cargo-wasi)

# 실행

wasmtime 런타임으로 실행할 수 있다.

```shell
wasmtime --invoke add_one ./target/wasm32-wasi/debug/wasi_lib_demo.wasm 4
```

[GitHub - bytecodealliance/wasmtime: Standalone JIT-style runtime for WebAssembly, using Cranelift](https://github.com/bytecodealliance/wasmtime)

# 참고

- [Rust - Wasmtime](https://bytecodealliance.github.io/wasmtime/wasm-rust.html)

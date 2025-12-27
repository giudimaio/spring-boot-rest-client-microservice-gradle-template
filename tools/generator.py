#!/usr/bin/env python3
import argparse
import os
import shutil
import fileinput
import tempfile
import re

EXTENSIONS = (".java", ".kt", ".kts", ".gradle", ".yml", ".yaml", ".properties", ".md")
TEMPLATE_ROOT_NAME = 'spring-boot-be-rest-client-template'
TEMPLATE_CORP_NAME = '__CORP_NAME__'
TEMPLATE_APP_NAME = '__APP_NAME__'

def copy_template(src, dst):
    for item in os.listdir(src):
        if item in ("out", ".git", "tools", "README.md"):
            continue
        s = os.path.join(src, item)
        d = os.path.join(dst, item)
        if os.path.isdir(s):
            shutil.copytree(s, d, dirs_exist_ok=True)
        else:
            os.makedirs(os.path.dirname(d), exist_ok=True)
            shutil.copy2(s, d)

def rename_directories(target_dir, corp, app):
    for root, dirs, files in os.walk(target_dir, topdown=False):
        for d in dirs:
            old_path = os.path.join(root, d)
            new_name = d.replace(TEMPLATE_CORP_NAME, corp).replace(TEMPLATE_APP_NAME, app)
            new_path = os.path.join(root, new_name)
            if old_path != new_path:
                os.rename(old_path, new_path)

def replace_placeholders(target_dir, corp, app, new_root_name):
    for root, dirs, files in os.walk(target_dir):
        for f in files:
            if f.endswith(EXTENSIONS):
                path = os.path.join(root, f)
                with fileinput.FileInput(path, inplace=True, backup=".bak") as file:
                    for line in file:
                        line = line.replace(TEMPLATE_CORP_NAME, corp).replace(TEMPLATE_APP_NAME, app).replace(TEMPLATE_ROOT_NAME, new_root_name)
                        print(line, end="")

def remove_backup_files(target_dir):
    for root, _, files in os.walk(target_dir):
        for f in files:
            if f.endswith(".bak"):
                os.remove(os.path.join(root, f))

def main():
    parser = argparse.ArgumentParser(description="Generate a Spring Boot project from template.")
    parser.add_argument("--program-name", required=True, help="Program name")
    parser.add_argument("--app-name", required=True, help="Application name")
    parser.add_argument("--corp-name", required=True, help="Corporation name")
    parser.add_argument("--out-dir", default="./out", help="Output directory (default: ./out)")
    parser.add_argument("--force", action="store_true", help="Overwrite if target exists")
    args = parser.parse_args()

    PROGRAM = args.program_name.lower().replace("-", "").replace("_", "")
    APP = args.app_name.lower().replace("-", "").replace("_", "")
    CORP = args.corp_name.lower().replace("-", "").replace("_", "")
    OUT_DIR = os.path.abspath(args.out_dir)

    TEMPLATE_ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))

    if not os.path.exists(TEMPLATE_ROOT):
        raise FileNotFoundError(f"Template folder not found at {TEMPLATE_ROOT}")

    NEW_ROOT_NAME = f"{PROGRAM}-be-{APP}-rest-client"
    FINAL_DIR = os.path.join(OUT_DIR, NEW_ROOT_NAME)

    if os.path.exists(FINAL_DIR):
        if args.force:
            shutil.rmtree(FINAL_DIR)
        else:
            raise FileExistsError(f"Directory '{FINAL_DIR}' already exists. Use --force to overwrite.")

    os.makedirs(OUT_DIR, exist_ok=True)

    with tempfile.TemporaryDirectory(dir=OUT_DIR) as tmp_dir:
        copy_template(TEMPLATE_ROOT, tmp_dir)
        rename_directories(tmp_dir, CORP, APP)
        replace_placeholders(tmp_dir, CORP, APP, NEW_ROOT_NAME)
        remove_backup_files(tmp_dir)
        tools_dir = os.path.join(tmp_dir, "tools")
        generator_script = os.path.join(tools_dir, "generate.py")
        if os.path.exists(generator_script):
            os.remove(generator_script)

        shutil.move(tmp_dir, FINAL_DIR)

    print(f"Project generated at {FINAL_DIR}")

if __name__ == "__main__":
    main()

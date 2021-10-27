#!/usr/bin/env bash
echo "Welcome to the challenge"
echo "You are in $PWD"

while :; do
  echo "Options:"
  echo "1) List files"
  echo "2) Read file"
  echo "3) Exit"

  read -rp "Choose option: " option

  if [[ $option -eq 1 ]]; then
    ls -la .
  elif [[ $option -eq 2 ]]; then
    read -rp "Filename: " filename

    regex="\.\.|;|&|\||\`|\{|}|'|\""
    if [[ "$filename" =~ $regex ]]; then
      echo "Incorrect file name"
      exit 0
    fi

    if [ "$filename" == "flag.txt" ]; then
      echo "Access denied"
      exit 0
    fi

    cat "$filename"
  else
    exit 0
  fi
done
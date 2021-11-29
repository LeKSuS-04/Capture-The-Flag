#!/bin/bash
echo "Welcome to ExternalShell v0.1"
read -rp "Enter pin: " pin
if [[ $pin -eq 13435923234231 ]]
then
  echo "Correct"
  echo "User: " $(id)
  echo "Directory: " $(pwd)
  exit 0
else
  echo "Wrong"
  exit 0
fi

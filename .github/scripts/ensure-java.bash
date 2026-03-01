#!/bin/bash

# Ensure required environment variable is set
if [ -z "$REQUIRED_VERSION" ]; then
  echo "Error: REQUIRED_VERSION environment variable is not set."
  exit 1
fi

# Construct the expected environment variable name for the GitHub Runner
# e.g., for Java 21, it looks for JAVA_HOME_21_X64
VAR_NAME="JAVA_HOME_${REQUIRED_VERSION}_X64"

# Indirect variable expansion to check if the specific JAVA_HOME variable exists
if [ -n "${!VAR_NAME}" ]; then
  echo "Java ${REQUIRED_VERSION} found on runner at ${!VAR_NAME}. Setting JAVA_HOME."
  
  # Set JAVA_HOME in GITHUB_ENV so subsequent steps use this JDK
  echo "JAVA_HOME=${!VAR_NAME}" >> $GITHUB_ENV
  
  # Output skipped=true so the 'Install Java' step can be skipped
  echo "skipped=true" >> $GITHUB_OUTPUT
else
  echo "Java ${REQUIRED_VERSION} not found on runner (checked ${VAR_NAME}). Will install."
  
  # Output skipped=false so the 'Install Java' step will run
  echo "skipped=false" >> $GITHUB_OUTPUT
fi

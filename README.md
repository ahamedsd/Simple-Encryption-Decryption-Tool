# Simple Encryption / Decryption Tool (Java)

## Overview
This project provides a lightweight file encryption and decryption tool built in Java. It allows users to securely encrypt files using a passcode and later decrypt them using the same passcode. The program also includes an option to delete encrypted or decrypted files when needed.

## Features
- Encrypt any file using a passcode.
- Decrypt previously encrypted files with the correct passcode.
- Delete files directly from the interface.
- Interactive menu-driven design.
- Uses AES encryption for secure handling of data.

## How It Works
1. The user is presented with a menu to choose between:
   - Encrypt a file  
   - Decrypt a file  
   - Delete a file  
   - Exit  
2. When encrypting or decrypting, the program requests a passcode. The same passcode must be used for decryption.
3. A new encrypted (`.enc`) or decrypted output file is generated automatically based on the original file name.
4. File deletion permanently removes the selected file.

## Requirements
- Java 11 or later
- Basic understanding of terminal/command line usage

## How to Run
1. Compile the Java file:
   ```bash
   javac EncryptTool.java
   ```
2. Run the program:
   ```bash
   java EncryptTool
   ```

## Notes
- Always keep your passcode safe. Files cannot be recovered without the correct passcode.
- Ensure you have permission before encrypting or deleting any file.

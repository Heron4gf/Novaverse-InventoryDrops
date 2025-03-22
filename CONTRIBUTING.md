# Contributing to InventoryDrops

Thank you for considering contributing to the InventoryDrops project! Your contributions are vital for the enhancement and maintenance of the plugin. Below are guidelines that will help you to get started.

## Table of Contents
- [Getting Started](#getting-started)
- [Code of Conduct](#code-of-conduct)
- [Reporting Issues](#reporting-issues)
- [Feature Requests](#feature-requests)
- [Pull Requests](#pull-requests)
- [Development Setup](#development-setup)

## Getting Started
1. **Fork the repository**: Click the "Fork" button on the top-right of the page to create your copy of the project.
2. **Clone your fork**: Use the following command to clone your fork to your local machine:

   ```bash
   git clone https://github.com/Heron4gf/InventoryDrops.git
   ```

3. **Create a new branch**: Before you start making changes, create a new branch with descriptive names:

   ```bash
   git checkout -b feature/your-feature-name
   ```

## Reporting Issues
If you encounter bugs or have problems with the plugin, please report them in the [Issues section](https://github.com/Heron4gf/InventoryDrops/issues).

1. **Describe the issue in detail**: Include steps to reproduce it, if applicable.
2. **Include logs**: If possible, attach any relevant logs.

## Feature Requests
We welcome feature suggestions! Please open a new issue in the repository and provide the following information:

- A brief description of the feature
- Use cases: Why is it useful?

## Pull Requests
To submit a pull request (PR):

1. Make your changes in a new branch.
2. Ensure your changes are tested and documented as needed.
3. Push your changes to your fork:

   ```bash
   git push origin feature/your-feature-name
   ```

4. Open a pull request against the main repository.
5. Add a detailed description of your changes and any relevant information.

## Development Setup
### Prerequisites
- Java Development Kit (JDK) version 17
- Gradle installed

### Building the Plugin
1. Navigate to the project directory.
2. Run the following command:

   ```bash
   ./gradlew build
   ```

The compiled plugin will be located in the `build/libs` directory.

### Running the Plugin
To test your changes, you should have a Minecraft server set up where you can deploy and run the plugin. Copy the built `.jar` file from `build/libs` to the server's `plugins` directory.

## Thank You!
We appreciate your interest in contributing to InventoryDrops. Every bit helps us improve the plugin further for the community!
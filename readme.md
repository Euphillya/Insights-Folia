# Essentials-Folia

This repository is a fork of [Insights](https://github.com/InsightsPlugin/Insights) specifically modified to work with Folia. The main difference lies in the use of a patch file to apply the necessary changes. This guide will explain how to set up, use, and maintain this fork.

## Prerequisites

Before starting, make sure you have the following tools installed on your system:

- Git
- Bash (if you are on Windows, you can use Git Bash or WSL)
- Java Development Kit 21 (JDK) to compile the plugin

## Download

You can download the plugins here: https://github.com/Euphillya/Insights-Folia/actions

## Installation

### Cloning the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/Euphillya/Insights-Folia.git
cd Insights-Folia
```

### Using the Script

A Bash script is provided to manage recloning, creating, and applying patches. Here's how to use it:

### Update the Repository by Recloning

To update the source code by deleting and recloning the original Insights repository:

```bash
./script.sh updateUpstream
```

This command will:
1. Remove the local directories `Insights` and `Insights-Patchs`.
2. Re-clone the `Insights` repository into the `Insights` directory.
3. Copy the contents of `Insights` to `Insights-Patchs`.

### Create Patches

To create patches from changes made in the `Insights-Patchs` directory:

```bash
./script.sh createPatches
```

Patches will be generated in the `patches/plugins` directory.

### Apply Patches

To apply existing patches to the `Insights-Patchs` directory:

```bash
./script.sh applyPatches
```

This command will apply each patch found in `patches/plugins` and create a commit for each applied patch.

## Directory Structure

- `Insights/`: Cloned directory from the original Insights repository.
- `Insights-Patchs/`: Working directory where modifications are made and patches are applied.
- `patches/plugins/`: Directory containing the generated patch files.

## Contributing

If you wish to contribute to this project, please follow these steps:

1. Fork this repository.
2. Create a branch for your feature (`git checkout -b my-feature`).
3. Make your changes in the `Insights-Patchs` directory.
4. Create patches with `./script.sh createPatches`.
5. Commit and push your patches to your fork.
6. Create a pull request on this repository.

## Help

For any questions or issues, feel free to open an [issue](https://github.com/Euphillya/Insights-Folia/issues).

## License

This project is licensed under the MIT License. For more information, please see the [LICENSE](LICENSE) file.
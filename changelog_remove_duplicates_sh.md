# Patch: Shell Script to Remove Duplicate Packages

This shell script deletes the following directories in one go:

- `app/src/main/java/com/example/app`
- `app/src/main/java/com/example/phoneforfilm/presentation`

**Usage:**
1. Copy `delete_duplicates.sh` to the root of your Android Studio project.
2. Run: `./delete_duplicates.sh`
3. The specified directories will be removed.

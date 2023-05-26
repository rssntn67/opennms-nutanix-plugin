## Nutanix Plugin Release Process:
In this example we are releasing version 1.0.1

1) run `mvn versions:set -DnewVersion=1.0.1` 
2) modify `doc/antora.yml` and change the version to `version: '1.0.1'`
3) commit changes `git commit -a -m "v2.0.0-SNAPSHOT -> v1.0.1`
4) tag the release `git tag v1.0.1`
5) push changes `git push && git push --tags`
6) run `mvn versions:set -DnewVersion=1.0.2-SNAPSHOT` 
7) modify `doc/antora.yml` and change the version to `version: '1.0.2-SNAPSHOT'`
8) commit changes `git commit -a -m "v1.0.1 -> v1.0.2-SNAPSHOT`
9) push changes `git push`

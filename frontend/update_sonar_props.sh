#!/usr/bin/env bash
echo "Updating the SonarQube properties..."

# Get the version from package.json
PACKAGE_VERSION=$(cat package.json \
  | grep version \
  | head -1 \
  | awk -F: '{ print $2 }' \
  | sed 's/[",]//g' \
  | tr -d '[[:space:]]')
echo "Extracted version: ${PACKAGE_VERSION}"

# Get the project name from package.json
PACKAGE_NAME=$(cat package.json \
  | grep name \
  | head -1 \
  | awk -F: '{ print $2 }' \
  | sed 's/[",]//g' \
  | tr -d '[[:space:]]')
echo "Extracted project: ${PACKAGE_NAME}"

# CREATE SONAR-SCANNER FROM TEMPLATE
cp sonar-project.properties.template sonar-project.properties

# Get the Sonar properties file
SONAR_FILE=$(find ./ -iname sonar-project.properties -type f)
echo "Sonar file found: ${SONAR_FILE}"

# Update the version
REPLACE='^sonar.projectVersion=.*$'
WITH="sonar.projectVersion=${PACKAGE_VERSION}"
sed -i.bak "s#${REPLACE}#${WITH}#g" ${SONAR_FILE}

# Update the project name
REPLACE='^sonar.projectName=.*$'
WITH="sonar.projectName=${PACKAGE_NAME}"
sed -i.bak "s#${REPLACE}#${WITH}#g" ${SONAR_FILE}

echo "Done!"
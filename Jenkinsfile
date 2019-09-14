pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/markhm/vaadin_examples', branch: 'master')
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean install -DskipTests'
      }
    }
    stage('Analyse') {
      steps {
        sh '''!#/bin/sh

mvn checkstyle:checkstyle'''
      }
    }
    stage('Test') {
      steps {
        sh '''#!/bin/sh

mvn test:test'''
      }
    }
  }
}
pipeline
{
  agent
  {
      docker
      {
          image 'maven:3-jdk-12'
          args '-v /Users/mark/.m2:/root/.m2'

          // host directory : guest directory
      }
  }

  stages
  {
    stage('Test environment')
      {
        steps
        {
          sh 'mvn version'
        }
      }

    stage('Checkout')
    {
      steps
      {
        git(url: 'https://github.com/markhm/vaadin_examples', branch: 'master')
      }
    }
    stage('Build')
    {
      steps
      {
        sh 'mvn -DskipTests clean package'
      }
    }
    stage('Analyse')
    {
      steps
      {
        sh '''!#/bin/sh
            mvn checkstyle:checkstyle'''
      }
    }
    stage('Test')
    {
      steps
      {
        sh '''#!/bin/sh
        mvn test:test'''
      }
    }
  }
}
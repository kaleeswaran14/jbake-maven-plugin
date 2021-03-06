package br.com.ingenieux.mojo.jbake;

/*
 * Copyright 2013 ingenieux Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.jbake.app.Oven;

/**
 * Runs jbake on a folder
 */
@Mojo(name = "generate")
public class GenerateMojo extends AbstractMojo {
	/**
	 * Location of the Output Directory.
	 */
	@Parameter(property = "jbake.outputDirectory", defaultValue = "${project.build.directory}/${project.build.finalName}", required = true)
	protected File outputDirectory;
	
	/**
	 * Location of the Output Directory.
	 */
	@Parameter(property = "jbake.inputDirectory", defaultValue = "${project.basedir}/src/main/jbake", required = true)
	protected File inputDirectory;
	
	public void execute() throws MojoExecutionException {
		try {
			Oven oven = new Oven(inputDirectory, outputDirectory);
			
			oven.setupPaths();
			oven.bake();
		} catch (Exception e) {
			getLog().info("Oops", e);
			
			throw new MojoExecutionException("Failure when running: ", e);
		}
	}
}

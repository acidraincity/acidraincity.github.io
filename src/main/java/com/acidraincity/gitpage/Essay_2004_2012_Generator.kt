package com.acidraincity.gitpage

import com.acidraincity.tool.IOTool
import java.io.File

class Essay_2004_2012_Generator {}

fun main( args : Array< String > ){
	File( "workspace/essay_2004_2012" ).listFiles{ f -> f.name.endsWith( ".jpg" ) }.forEach {
		f ->
		val str = """![](./${f.name})
			|
			|[다음](${Integer.parseInt(f.nameWithoutExtension) + 1})
		""".trimMargin()
		IOTool.writeStringToFile( File(f.nameWithoutExtension + ".md"), str, "UTF-8")
	}
}
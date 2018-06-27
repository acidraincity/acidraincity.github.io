package com.acidraincity.gitpage

import com.acidraincity.tool.IOTool
import org.apache.commons.lang3.StringUtils
import java.io.File

class Essay_2004_2012_Generator {}

fun main( args : Array< String > ){
	File( "workspace/essay_2004_2012" ).listFiles{ f -> f.name.endsWith( ".jpg" ) }.forEach {
		f ->
		val idx = Integer.parseInt(f.nameWithoutExtension)
		val nextIdx = idx + 1
		val str = """
			|![](./${f.name})
			|
			|[다음](${StringUtils.leftPad("${nextIdx}", 3, '0' )})
		""".trimMargin()
		IOTool.writeStringToFile( File( f.parentFile, "${f.nameWithoutExtension}.md" ), str, "UTF-8")
	}
}
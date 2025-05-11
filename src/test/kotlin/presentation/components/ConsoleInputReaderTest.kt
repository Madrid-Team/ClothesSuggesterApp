package presentation.components

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test

class ConsoleInputReaderTest {

 private lateinit var reader: InputReader

 @BeforeEach
 fun setup() {
  reader = ConsoleInputReader()
 }


 @Test
 fun `should return input when readInput is called`() {
  // given
  val input = "Clothes Suggester App"
  val inputStream = ByteArrayInputStream(input.toByteArray())
  System.setIn(inputStream)

  // when
  val result = reader.readInput("enter your name")

  // then
  assertThat(result).isEqualTo(input)
 }


 @Test
 fun `should return empty string when input is null`() {
  // given
  val inputStream = ByteArrayInputStream(byteArrayOf())
  System.setIn(inputStream)

  // when
  val result = reader.readInput("enter your name")

  // then
  assertThat(result).isEqualTo("")
 }

 @Test
 fun `should print hint when hint is not empty`() {
  // given
  val hint = "enter your name"
  val input = "Clothes Suggester App"
  val inputStream = ByteArrayInputStream(input.toByteArray())
  System.setIn(inputStream)
  val outputStream = ByteArrayOutputStream()
  System.setOut(PrintStream(outputStream))

  // when
  reader.readInput(hint)

  // then
  assertThat(outputStream.toString()).contains(hint)
 }

 @Test
 fun `should not print anything when hint is empty`() {
  // given
  val hint = ""
  val input = "Clothes Suggester App"
  val inputStream = ByteArrayInputStream(input.toByteArray())
  System.setIn(inputStream)
  val outputStream = ByteArrayOutputStream()
  System.setOut(PrintStream(outputStream))

  // when
  reader.readInput(hint)

  // then
  assertThat(outputStream.toString()).isEmpty()
 }
}
package generateMonthlyPayslip

import scala.util.{Try,Failure,Success}

case class GenerateMonthlyPayslip(name: String, annualIncome: Int) {
  val formatter = java.text.NumberFormat.getCurrencyInstance

  def displayName: String = s"Monthly Payslip for: ${name}"

  def displayGrossMonthlyIncome: String = {
    val grossMonthlyIncome = formatter.format(annualIncome / 12.0)
    s"Gross Monthly Income: ${grossMonthlyIncome}"
  }

  def displayMonthlyIncomeTax: String = {
    val grossMonthlyIncome = formatter.format(annualTax / 12.0)
    s"Monthly Income Tax: ${grossMonthlyIncome}"
  }

  def displayNetMonthlyIncome: String = {
    val netMonthlyIncome = formatter.format((annualIncome - annualTax) / 12.0)
    s"Net Monthly Income: ${netMonthlyIncome}"
  }

  def annualTax: Int = {
    (
      annualTaxForLessThan20k + 
      annualTaxForBetween20kAnd40k + 
      annualTaxForBetween40kAnd80k + 
      annualTaxForBetween80kAnd180k +
      annualTaxOver180k
    ).toInt
  }

  protected  
  
  def annualTaxForLessThan20k: Double = {
    // tax free income is $0 - $20000
    0
  }

  def annualTaxForBetween20kAnd40k: Double = {
    // tax for income between $20001 to $40000 is 10c for each $1 over $20000
    val taxableIncome = if (annualIncome > 40000) {
        40000 - 20000
      } else annualIncome - 20000
    val result = if (taxableIncome > 0) {
        taxableIncome * 0.1
      }
      else 0
    // println(s"annualTaxForBetween20kAnd40k: ${result}")
    result
  } 

  def annualTaxForBetween40kAnd80k: Double = {
    // tax for income between $40001 to $80000 is 20c for each $1 over $40000
    val taxableIncome = if (annualIncome > 80000) {
        80000 - 40000
      } else annualIncome - 40000
    val result = if (taxableIncome > 0) {
        taxableIncome * 0.2
      }
      else 0
    // println(s"annualTaxForBetween40kAnd80k: ${result}")
    result
  } 

  def annualTaxForBetween80kAnd180k: Double = {
    // tax for income between $80001 to $180000 is 30c for each $1 over $80000
    val taxableIncome = if (annualIncome > 180000) {
        180000 - 80000
      } else annualIncome - 80000
    val result = if (taxableIncome > 0) {
        taxableIncome * 0.3
      }
      else 0
    // println(s"annualTaxForBetween80kAnd180k: ${result}")
    result
  } 

  def annualTaxOver180k: Double = {
    // tax for income of $180001 and over is 40c for each $1 over $180000
    val taxableIncome = annualIncome - 180000
    val result = if (taxableIncome > 0) {
        taxableIncome * 0.4
      }
      else 0
    // println(s"annualTaxForBetween80kAnd180k: ${result}")
    result
  } 
}

object GenerateMonthlyPayslip extends App {
  def displayUsage:Unit = {
    println("\nUsage: GenerateMonthlyPayslip <NAME> <SALARY AS INTEGER VALUE>")
    println("\nexample: GenerateMonthlyPayslip \"Mary Song\" 60000")
    println("\n This script outputs a monthly pay slip given an employee name and annual salary. \n")
  }

  if (args.length >= 2) {
    Try(args(1).toInt) match {
      case Failure(exception) => {
        println(s"\nERROR: Failed to generate payslip for ${args(0)}. '${args(1)}' is not an Integer. Salary must be an Integer value.")
        displayUsage
      }
      case Success(value) => {
        val payslip = new GenerateMonthlyPayslip(args(0), args(1).toInt)

        println(payslip.displayName)
        println(payslip.displayGrossMonthlyIncome)
        println(payslip.displayMonthlyIncomeTax)
        println(payslip.displayNetMonthlyIncome)
        // println(s"annual tax: ${payslip.annualTax}")
      }
    }
  }
  else displayUsage
}
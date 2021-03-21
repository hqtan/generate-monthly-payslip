package generateMonthlyPayslip

import org.scalatest._
import flatspec.AnyFlatSpec
import matchers._

class GenerateMonthlyPayslipSpec extends AnyFlatSpec with should.Matchers {
  "The payslip" should "display employee name" in {
    val name = "Mary Song"
    val payslip = GenerateMonthlyPayslip(name, 10000)
    payslip.displayName shouldEqual s"Monthly Payslip for: ${name}"
  }

  "The payslip" should "display employee's gross monthly income" in {
    val name = "Mary Song"
    val annualIncome:Int = 60000
    val payslip = GenerateMonthlyPayslip(name, annualIncome)
    val formatter = java.text.NumberFormat.getCurrencyInstance
    val grossMonthlyIncome = formatter.format(annualIncome / 12.0)
    payslip.displayGrossMonthlyIncome shouldEqual s"Gross Monthly Income: ${grossMonthlyIncome}"
  }

  "The tax for income of $20,000 or less" should "be $0" in {
    val name = "Mary Song"
    val annualIncome:Int = 10000
    val payslip = GenerateMonthlyPayslip(name, annualIncome)
    val incomeTax = 0
    assert(payslip.annualTax === incomeTax)
  }

  "The tax for income between $20,001 and $40,000" should "be 10c for each $1 over $20,000" in {
    var name = "Mary Song"
    var annualIncome:Int = 20001
    var payslip = GenerateMonthlyPayslip(name, annualIncome)
    var incomeTax = (annualIncome - 20000) * 0.1
    assert(payslip.annualTax === incomeTax.toInt)

    annualIncome = 30000
    payslip = GenerateMonthlyPayslip(name, annualIncome)
    incomeTax = (annualIncome - 20000) * 0.1
    assert(payslip.annualTax === incomeTax.toInt)

    annualIncome = 40000
    payslip = GenerateMonthlyPayslip(name, annualIncome)
    incomeTax = (annualIncome - 20000) * 0.1
    assert(payslip.annualTax === incomeTax.toInt)
  }


  "The tax for income between $40,001 and $80,000" should "be 20c for each $1 over $40,000, " +
    "in addition of the tax from other lower tax brackets" in {
    var name = "Mary Song"
    var annualIncome:Int = 40001
    var payslip = GenerateMonthlyPayslip(name, annualIncome)
    var incomeTax = (annualIncome - 40000) * 0.2 + ((40000 - 20000) * 0.1)
    assert(payslip.annualTax === incomeTax.toInt)

    annualIncome = 60000
    payslip = GenerateMonthlyPayslip(name, annualIncome)
    incomeTax = (annualIncome - 40000) * 0.2 + ((40000 - 20000) * 0.1)
    assert(payslip.annualTax === incomeTax.toInt)

    annualIncome = 80000
    payslip = GenerateMonthlyPayslip(name, annualIncome)
    incomeTax = (annualIncome - 40000) * 0.2 + ((40000 - 20000) * 0.1)
    assert(payslip.annualTax === incomeTax.toInt)
  }

  "The tax for income between $80,001 and $180,000" should "be 30c for each $1 over $80,000, " +
    "in addition of the tax from other lower tax brackets" in {
    var name = "Mary Song"
    var annualIncome:Int = 80001
    var payslip = GenerateMonthlyPayslip(name, annualIncome)
    var incomeTax = (annualIncome - 80000) * 0.3 + ((80000 - 40000) * 0.2) + ((40000 - 20000) * 0.1)
    assert(payslip.annualTax === incomeTax.toInt)

    annualIncome = 100000
    payslip = GenerateMonthlyPayslip(name, annualIncome)
    incomeTax = (annualIncome - 80000) * 0.3 + ((80000 - 40000) * 0.2) + ((40000 - 20000) * 0.1)
    assert(payslip.annualTax === incomeTax.toInt)

    annualIncome = 180000
    payslip = GenerateMonthlyPayslip(name, annualIncome)
    incomeTax = (annualIncome - 80000) * 0.3 + ((80000 - 40000) * 0.2) + ((40000 - 20000) * 0.1)
    assert(payslip.annualTax === incomeTax.toInt)
  }

  "The tax for income of $180,001 and over" should "be 40c for each $1 over $180,000, " +
    "in addition of the tax from other lower tax brackets" in {
    var name = "Mary Song"
    var annualIncome:Int = 180001
    var payslip = GenerateMonthlyPayslip(name, annualIncome)
    var incomeTax = (annualIncome - 180000) * 0.4 + ((180000 - 80000) * 0.3) + ((80000 - 40000) * 0.2) + ((40000 - 20000) * 0.1)
    assert(payslip.annualTax === incomeTax.toInt)

    annualIncome = 1000000
    payslip = GenerateMonthlyPayslip(name, annualIncome)
    incomeTax = (annualIncome - 180000) * 0.4 + ((180000 - 80000) * 0.3) + ((80000 - 40000) * 0.2) + ((40000 - 20000) * 0.1)
    assert(payslip.annualTax === incomeTax.toInt)
  }
}

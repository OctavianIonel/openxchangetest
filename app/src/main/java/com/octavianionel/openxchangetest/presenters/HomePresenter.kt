package com.octavianionel.openxchangetest.presenters

import android.content.Context
import android.os.AsyncTask
import com.octavianionel.openxchangetest.contracts.BasePresenter
import com.octavianionel.openxchangetest.contracts.HomeContract
import com.octavianionel.openxchangetest.model.Earthquake
import com.octavianionel.openxchangetest.utils.DatabaseHandler
import com.octavianionel.openxchangetest.utils.Utils
import com.opencsv.CSVReader
import java.io.BufferedInputStream
import java.io.File
import java.io.FileReader
import java.net.URL


class HomePresenter(view: HomeContract.TheView): BasePresenter<HomeContract.TheView>(view), HomeContract.Presenter {

    override fun detach() {
        view = null
    }

    override fun downloadCsvFile(url: String) {

        DownloadCsvFromUrl(view).execute(url)
    }

    class DownloadCsvFromUrl(val view: HomeContract.TheView?): AsyncTask<String, String, String>() {

        override fun doInBackground(vararg p0: String?): String {
            val url  = URL(p0[0])
            val connection = url.openConnection()
            connection.connect()
            val inputStream = BufferedInputStream(url.openStream())
            val filename = Utils.FILE_SAVED
            val outputStream = view?.getTheContext()?.openFileOutput(filename, Context.MODE_PRIVATE)
            val data = ByteArray(1024)
            var count = inputStream.read(data)
            var total = count
            while (count != -1) {
                outputStream?.write(data, 0, count)
                count = inputStream.read(data)
                total += count
            }
            outputStream?.flush()
            outputStream?.close()
            inputStream.close()
            println("finished saving earthquake.csv to internal storage")
            return "Success"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            view?.finishedDownloadingCsv()
        }

    }

    override fun readSavedCsvFile(context: Context?) {
        val yourFilePath = context?.getFilesDir().toString() + "/" + Utils.FILE_SAVED
        val yourFile = File(yourFilePath)
        val reader = CSVReader(FileReader(yourFile))

        val myEntries = reader.readAll()
        myEntries.removeAt(0)

        val databaseHandler: DatabaseHandler = DatabaseHandler(view?.getTheContext())
        for (line in myEntries) {
            val earthquake: Earthquake? = Earthquake()
            earthquake?.time = line[0]
            earthquake?.latitude = line[1]
            earthquake?.longitude = line[2]
            earthquake?.depth = line[3]
            earthquake?.mag = line[4]
            earthquake?.magType = line[5]
            earthquake?.nst = line[6]
            earthquake?.gap = line[7]
            earthquake?.dmin = line[8]
            earthquake?.rms = line[9]
            earthquake?.net = line[10]
            earthquake?.id = line[11]
            earthquake?.updated = line[12]
            earthquake?.place = line[13]
            earthquake?.type = line[14]
            earthquake?.horizontalError = line[15]
            earthquake?.depthError = line[16]
            earthquake?.magError = line[17]
            earthquake?.magNst = line[18]
            earthquake?.status = line[19]
            earthquake?.locationSources = line[20]
            earthquake?.magSource = line[21]
            databaseHandler.addEarthquake(earthquake)
        }

        view?.finishedSavingToDb(databaseHandler.allEarthquakes)
    }


}
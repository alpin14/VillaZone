package com.proyektingkat2.villazone.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.proyektingkat2.villazone.LoginActivity
import com.proyektingkat2.villazone.databinding.FragmentProfileBinding
import java.io.ByteArrayOutputStream


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    lateinit var auth: FirebaseAuth
    private lateinit var imgUri : Uri

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null){
            binding.editTextEmail.setText(user.email)
        }

        binding.imgUser.setOnClickListener{
            getCamera()
        }

        binding.btnLogout.setOnClickListener {
            btnLogout()
        }
    }

    private fun getCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            activity?.packageManager?.let {
                intent?.resolveActivity(it).also {
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap

            upldImageToFirebase(imgBitmap)
        }
    }

    private fun upldImageToFirebase(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img_admin/${FirebaseAuth.getInstance().currentUser?.email}")
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        val img = baos.toByteArray()
        ref.putBytes(img)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    ref.downloadUrl.addOnCompleteListener{ Task ->
                        Task.result.let { Uri ->
                            imgUri = Uri
                            binding.imgUser.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }
    }

    private fun btnLogout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()

        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val REQUEST_CAMERA = 100
    }
}